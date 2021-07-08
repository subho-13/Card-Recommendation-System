import React, { useState, useEffect } from "react"
import axios from "axios"

function Recommendation(props) {
    const [data, setData] = useState()

    useEffect(() => {
        axios
            .get("http://localhost:9506/recommendation/" + props.customerID)
            .then((response) => {
                console.log(response.data.modelCardMap)
                setData(response.data.modelCardMap)
            })
            .catch((err) => {
                console.log(err)
            })
    }, [props.customerID])

    const getRecommendationDivs = (recommendations) => {
        const divlist = []
        for (var key in recommendations) {
            console.log(key, recommendations[key])
            divlist.push(
                <div key={key}>
                    <span>{key}</span>
                    <span> : </span>
                    <span>{recommendations[key]}</span>
                </div>
            )
        }

        return divlist.map((item) => {
            return item
        })
    }

    return (
        <div>
            <div>{getRecommendationDivs(data)}</div>
        </div>
    )
}

export default Recommendation
