import React from 'react'
import { Chart } from 'react-google-charts'

function ShoppingPattern(props) {
    return <Chart
        width={'500px'}
        height={'300px'}
        chartType='BarChart'
        loader={<div>Loading Shopping Pattern</div>}
        data={[
            ['Category', 'Expenditure'],
            ...Object.entries(props.purchaseExpenditureMap)
        ]}
    />
}

export default ShoppingPattern