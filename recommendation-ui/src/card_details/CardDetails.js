import React from 'react'
import beautify from '../util/Functions'
import './CardDetails.css'

function CardDetails(props) {
    console.log(props)
    const cardDivs = props.cards.map(cardName => {
        if (props.complimentaryCard === true) {
            return <div className='card-details-row-complimentary' key={cardName}>{beautify(cardName)}</div>
        }

        return <div className='card-details-row' key={cardName}>{beautify(cardName)}</div>
    })


    return <div className='card-details-table'>
        <div className='card-details-header'>Card Recommendations</div>
        {cardDivs}
   </div>
}

export default CardDetails