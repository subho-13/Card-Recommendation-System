import React, {useState, useEffect} from "react";
import { Bar, Doughnut } from 'react-chartjs-2';

const CardConf = ({customerID}) => {
  
    useEffect(() => {

    }, [])

    const datax = [10, 10, 10, 10, 10, 10, 10, 10, 10]

    const data = {
        labels: [
          'CASH_WISE',
          'PLATINUM',
          'HOTEL',
          'COLLEGE',
          'VISA_SIGNATURE',
          'HOLIDAY',
          'SHOPPING',
          'ENTERTAINMENT',
          'CREDIT_BUILDER',
        ],
        datasets: [
          {
            label: 'Card Confidence',
            data: datax,
            backgroundColor: [
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',
              'rgba(253, 35, 35, 0.2)',
              'rgba(255, 238, 112, 0.6)',              
            ],
            borderColor: [
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',
              'rgba(253, 35, 35, 1)',
              'rgba(255, 238, 112, 1)',          
            ],
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
            text: 'Purchase Category Map',
          },
        },
      };

    return (
      <div className="App">    
            {customerID}
            <Doughnut data={data} options={options} />
      </div>
    );
  }
  
  export default CardConf;