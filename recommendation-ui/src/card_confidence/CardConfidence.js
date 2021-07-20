import React from 'react'
import './CardConfidence.css'
import  beautify from '../util/Functions'

function CardConfidence(props) {
    var cardConfidenceArray = Object.entries(props.cardConfidenceMap)
    const cardConfidenceTable = cardConfidenceArray.sort((a, b) => {
        return b[1] - a[1]
    }).map(elem => {
        return <div className='row' key={elem[0]}>
            <div className='key'>{beautify(elem[0])}</div>
            <div className='value'>{elem[1]}</div>
        </div>
    })

    return <div className='card-confidence-table'>
        <div className='heading'>
            <div className='key'>Model Name</div>
            <div className='value'>Confidence Score</div>
        </div>
        {cardConfidenceTable}
    </div>
}

export default CardConfidence