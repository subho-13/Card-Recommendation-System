import React from 'react'

function CardConfidence(props) {
    const cardConfidence = props.cardConfidenceMap
    const cardConfidenceTable = Object.keys(cardConfidence).map(key => {
        return <div>
            <div>{key}</div>
            <div>{cardConfidence[key]}</div>
        </div>
    })

    return <div>
        <div>Model Name</div>
        <div>Confidence Score</div>
        {cardConfidenceTable}
    </div>
}

export default CardConfidence