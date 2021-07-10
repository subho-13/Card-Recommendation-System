package com.wf.nearlinescheduler.service;

import com.wf.contractlib.contracts.NearlineTrigger;
import com.wf.nearlinescheduler.entity.CustomerDetails;
import com.wf.nearlinescheduler.repository.CustomerDetailsRepository;
import com.wf.nearlinescheduler.util.Time;
import lombok.SneakyThrows;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Configurable
public class LastScheduledTrigger  implements DisposableBean, Runnable {
    @Value("${sleeptime-ms}")
    private int sleeptime;

    @Value("${last-schedule-interval-s}")
    private int lastScheduleInterval;

    private Thread thread;
    private CustomerDetailsRepository customerDetailsRepository;
    private TriggerProducer triggerProducer;

    @Autowired
    public LastScheduledTrigger(ApplicationContext applicationContext) {
        this.customerDetailsRepository = applicationContext.getBean(CustomerDetailsRepository.class);
        this.triggerProducer = applicationContext.getBean(TriggerProducer.class);

        this.thread = new Thread(this);
        this.thread.start();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            List<CustomerDetails> customerDetailsList = getCustomersToSchedule();

            for(CustomerDetails customer: customerDetailsList) {
                NearlineTrigger nearlineTrigger = new NearlineTrigger();
                nearlineTrigger.setCustomerID(customer.getCustomerID());

                triggerProducer.produce(nearlineTrigger);

                customer.setLastScheduledUnixTime(Time.getCurrentTimeInSecs());
                customerDetailsRepository.save(customer);
            }

            Thread.sleep(sleeptime);
        }
    }

    private List<CustomerDetails> getCustomersToSchedule() {
        long currentTimeSec = Time.getCurrentTimeInSecs();
        return customerDetailsRepository.findByLastScheduledUnixTimeLessThan(currentTimeSec - lastScheduleInterval);
    }

    @Override
    public void destroy() throws Exception {}
}