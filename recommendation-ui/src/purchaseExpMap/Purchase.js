import React, {useState, useEffect} from "react";
import { Bar } from 'react-chartjs-2';
import axios from "axios"

const Purchase = ({customerID}) => {
    const [datax, setDataxChart] = useState();
    const [labels, setLabelsChart] = useState();
    const [backgroundColor, setBackgroundColor] = useState();
    const [borderColor, setBorderColor] = useState();

    const globalLabels = [
          'EDUCATION',
          'ENTERTAINMENT',
          'FOOD',
          'GAS_TRANS',
          'GROCERY_NET',
          'GROCERY_POS',
          'HEALTH',
          'HOME',
          'HOTEL',
          'KIDS_PETS',
          'MISC_NET',
          'MISC_POS',
          'PERSONAL',
          'SHOP_NET',
          'SHOP_POS',
          'TRAVEL'
        ]

    useEffect(() => {
        axios.get(`http://localhost:9508/get/${customerID}`)
          .then((response) => {
                console.log(response.data.purchaseExpenditureMap)
                const temp_purchaseExpenditureMap = response.data.purchaseExpenditureMap;

                let temp_datax = [];                
                let temp_labels = [];
                let temp_borderColor = []
                let temp_backgroundColor = []
                let cnt = 0;
                for(let i = 0; i < globalLabels.length; i++){
                    if(globalLabels[i] in temp_purchaseExpenditureMap){
                        cnt++;
                        temp_labels.push(globalLabels[i]);
                        temp_datax.push(temp_purchaseExpenditureMap[globalLabels[i]]);

                        if(cnt % 2 == 0){
                            temp_backgroundColor.push('rgba(253, 35, 35, 0.2)');
                            temp_borderColor.push('rgba(253, 35, 35, 1)');
                        }else {
                            temp_backgroundColor.push('rgba(255, 238, 112, 0.6)');
                            temp_borderColor.push('rgba(255, 238, 112, 1)');
                        }
                    }                    
                }

                setLabelsChart(temp_labels);
                setDataxChart(temp_datax);
                setBackgroundColor(temp_backgroundColor);
                setBorderColor(temp_borderColor);
            })
            .catch((err) => {
                console.log(err)
            })
    }, [customerID])    

    const data = {
        labels: labels,
        datasets: [
          {
            label: 'Amount Spent per Category',
            data: datax,
            backgroundColor: backgroundColor,
            borderColor:  borderColor,
            borderWidth: 2,
          },
        ],
      };

    const options = {
        indexAxis: 'y',
        // Elements options apply to all of the options unless overridden in a dataset
        // In this case, we are setting the border of each horizontal bar to be 2px wide
        elements: {
          bar: {
            borderWidth: 2,
          },
        },
        responsive: true,
        plugins: {
          legend: {
            position: 'right',
          },
          title: {
            display: true,
            text: 'Purchase Category',
          },
        },
      };

    return (
      <div className="App">    
            {customerID}
            <Bar data={data} options={options} />
      </div>
    );
  }
  
  export default Purchase;