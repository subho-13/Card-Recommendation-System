import React from 'react'
import './CardConfidence.css'
import  beautify from '../util/Functions'

function CardConfidence(props) {
    var cardConfidenceArray = Object.entries(props.cardConfidenceMap)
    const cardConfidenceTable = cardConfidenceArray.sort((a, b) => {
        return b[1] - a[1]
    }).map(elem => {
        return <div className='row' key={elem[0]}>
            <div className='model'>{beautify(elem[0])}</div>
            <div className='score'>{elem[1].toFixed(4)}</div>
        </div>
    })

    return <div className='product-suggestions-table'>
        <div className='heading'>
            <div className='model'>Card Name</div>
            <div className='score'>Confidence Score</div>
        </div>
        {cardConfidenceTable}
    </div>
}

export default CardConfidence