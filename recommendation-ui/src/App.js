import axios from "axios";
import React, { useState } from "react";

import SearchBar from "./search_bar/SearchBar.js";
import CardDetails from "./card_details/CardDetails";
import ShoppingPattern from "./purchase_expenditure_map/ShoppingPattern";
import CardConfidence from "./card_confidence/CardConfidence";
import beautify from "./util/Functions.js";
import ProductDetails from "./product_details/ProductDetails";
import "./App.css"

async function getCustomerDetails(customerID) {
  var recommendationDetails = axios
    .get(`http://localhost:9508/get/${customerID}`)
    
  var productDetails = axios
  .get(`http://localhost:9503/get/${customerID}`)

  const customerDetails = axios.all([productDetails, recommendationDetails]).then(
    axios.spread((...responses) => {
        const check =  responses.map((response) => {
            return response.data
        })

        return check
    })
  ).then((detail) => {
    console.log(detail)
    return {
      ...detail[0],
      ...detail[1]
    }
  })

  return customerDetails
}

function App() {
  const [customerDetails, setCustomerDetails] = useState({
      complimentaryCard: false,
      creditScore: 0,
      rewardPoints: 0,
      existingCard: 'None',
      job: 'None',
      cardConfidenceMap: {},
      purchaseExpenditureMap: {},
      cards: []
  })

  const handleSubmit = (customerDetails) => {
    const customerID = customerDetails.customerID;
    getCustomerDetails(customerID).then(customerDetails => {
      console.log(customerDetails)
      setCustomerDetails({...customerDetails})
    })
  };

  return (
    <div className='root-container'>
      <div className="top-bar">
        <div className="customer-id">
          <SearchBar onSubmit={handleSubmit} />
        </div>
        <div className="credit-score">
          <div className="field-name">Credit Score</div>
          <div className="field-value">{customerDetails.creditScore}</div>
        </div>
        <div className="reward-points">
          <div className="field-name">Reward Points</div>
          <div className="field-value">{customerDetails.rewardPoints.toFixed(2)}</div>
        </div>
        <div className="profession">
          <div className="field-name">Profession</div>
          <div className="field-value">{beautify(customerDetails.job)}</div>
        </div>
        <div className="existing-card">
          <div className="field-name">Existing Card</div>
          <div className="field-value">{beautify(customerDetails.existingCard)}</div>
        </div>
      </div>
      <div className="details">
        <div className='card-confidence'>
          <CardConfidence cardConfidenceMap={customerDetails.cardConfidenceMap} />
        </div>
        <div className='purchase-expenditure'>
          <ShoppingPattern purchaseExpenditureMap={customerDetails.purchaseExpenditureMap} />
        </div>
        <div className='card-details'>
          <CardDetails
            cards={customerDetails.cards}
            complimentaryCard={customerDetails.complimentaryCard}
          />
        </div>
        <div className='product-suggestions'>
          <ProductDetails productSuggestions={customerDetails.productSuggestions} />
        </div>
      </div>
    </div>
  );
}

export default App;
