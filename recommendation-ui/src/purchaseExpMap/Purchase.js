import React, {useState, useEffect} from "react";
import { Bar } from 'react-chartjs-2';

const Purchase = ({customerID}) => {

    useEffect(() => {
        
    }, [customerID])

    const datax = [10, 10, 10, 10, 10, 10, 10, 10,10, 10, 10, 10, 10, 10, 10, 10]

    const data = {
        labels: ['EDUCATION',
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
        ],
        datasets: [
          {
            label: 'Amount Spent per Category',
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