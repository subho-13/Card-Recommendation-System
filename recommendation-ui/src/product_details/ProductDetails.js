import React from 'react'
import  beautify from '../util/Functions'
import "./ProductDetails.css"

function ProductDetails(props) {
    var productSuggestions = props.productSuggestions

    if( typeof productSuggestions === 'undefined') {
        productSuggestions = []
    }
    
    if (productSuggestions.length === 0) {
        productSuggestions = [{
            'product_id': 0,
            'partner_company': 'None',
            'product_name': 'None',
            'product_category': 'None',
            'reward_points_required': 0
        }]
    }
    
    const productSuggestionsTable = productSuggestions.sort((a, b) => {
        return b['reward_points_required'] - a['reward_points_required']
    }).map(elem => {
        return <div className='product-row' key={elem['product_id']}>
            <div className='partner-company'>{beautify(elem['partner_company'])}</div>
            <div className='product-name'>{beautify(elem['product_name'])}</div>
            <div className='product-category'>{beautify(elem['product_category'])}</div>
            <div className='reward-points-required'>{elem['reward_points_required']}</div>
        </div>
    })

    return <div className='card-confidence-table'>
        <div className='product-heading'>
            <div className='partner-company'>Partner Company</div>
            <div className='product-name'>Product Name</div>
            <div className='product-category'>Product Category</div>
            <div className='reward-points-required'>Required Reward Points</div>
        </div>
        {productSuggestionsTable}
    </div>
}

export default ProductDetails