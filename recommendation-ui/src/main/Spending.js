import React, { useState } from "react"
import { Chart } from "react-google-charts"
import axios from "axios"

function Spending(props) {
    const getCategoryExpenditureList = (categoryExpenditureMap) => {
        var categoryExpenditureList = []
        for (var category in categoryExpenditureMap) {
            var list = []
            list.push(category)
            list.push(categoryExpenditureMap[category])
            categoryExpenditureList.push(list)
        }

        return categoryExpenditureList
    }

    return (
        <div>
            <Chart
                width={"500px"}
                height={"300px"}
                chartType="BarChart"
                loader={<div>Loading Spending Pattern</div>}
                data={[
                    ["City", "2010 Population", "2000 Population"],
                    ["New York City, NY", 8175000, 8008000],
                    ["Los Angeles, CA", 3792000, 3694000],
                    ["Chicago, IL", 2695000, 2896000],
                    ["Houston, TX", 2099000, 1953000],
                    ["Philadelphia, PA", 1526000, 1517000],
                ]}
                options={{
                    title: "Spending Pattern",
                    chartArea: { width: "50%" },
                    hAxis: {
                        title: "Total Expenditure",
                        minValue: 0,
                    },
                    vAxis: {
                        title: "Category",
                    },
                }}
            />
            <div>
                <div>{featureVector["job"]}</div>
                <div>{featureVector["creditScore"]}</div>
                <div>{}</div>
            </div>
        </div>
    )
}

export default Spending
