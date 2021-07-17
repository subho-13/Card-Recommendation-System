import React, {useState} from 'react'
import { Chart } from "react-google-charts"


function CardDetails(props) {
    return <div>
        <Chart
            width={'50rem'}
            height={'50rem'}
            chartType="PieChart"
            loader={<div>Loading Charts</div>}
            data={[
                ['Card', 'Confidence Score'],
                ...Object.entries(props.cardConfidenceMap)
            ]}
            options={
                {
                    title: "Card & Confidence",
                    pieHole: 0.4
                }
            }
        />
    </div>
}

export default CardDetails