import React from 'react'

function CardDetails(props) {

    const cardDivs = props.cards.map(cardName => {
        if (props.isComplimentaryCard === true) {
            return <div>{cardName}</div>
        }

        return <div>{cardName}</div>
    })


    return <div>
        <div>Card Recommendations</div>
        {cardDivs}
   </div>
}

export default CardDetails