import React from 'react'
import { Chart } from 'react-google-charts'
import beautify from '../util/Functions'

import './SpendingPattern.css'

function ShoppingPattern(props) {
    return <Chart
        width={'60rem'}
        height={'30rem'}
        chartType='BarChart'
        loader={<div>Loading Shopping Pattern</div>}
        data={[
            ['Category', 'Expenditure'],
            ...Object.entries(props.purchaseExpenditureMap).map(item => {
                return [beautify(item[0]), item[1]]
            })
        ]}
        options={
            {
                title: 'Expenditure Pattern',
                titleTextStyle : {
                    fontName : 'Xanh Mono',
                    fontSize : 20
                },
                chartArea: {
                    width: '60%'
                },
                colors: ['#d71e2b'],
                vAxis : {
                    textStyle : {
                        fontName : 'Fira Code',
                        fontSize : 17
                    },
                    title: 'Category',
                    titleTextStyle : {
                        fontName : 'Xanh Mono',
                        fontSize : 20
                    }
                },
                legend : {
                    textStyle : {
                        fontName : 'Fira Code',
                        fontSize : 17
                    }
                },
                hAxis : {
                    textStyle : {
                        fontName : 'Fira Code',
                        fontSize : 17
                    },
                    title: 'Expenditure',
                    titleTextStyle : {
                        fontName : 'Xanh Mono',
                        fontSize : 20
                    }
                }
            }
        }
        className="spending-pattern"
    />
}

export default ShoppingPattern