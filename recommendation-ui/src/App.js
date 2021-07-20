import axios from "axios";
import React, { useState } from "react";

import SearchBar from "./search_bar/SearchBar";
import CardDetails from "./card_details/CardDetails";
import ShoppingPattern from "./purchase_expenditure_map/ShoppingPattern";
import CardConfidence from "./card_confidence/CardConfidence";
import ProductDetails from "./product_details/ProductDetails";
import "./App.css"

async function getCustomerDetails(customerID) {
  var customerDetails = await axios
    .get(`http://localhost:9508/get/${customerID}`)
    .then((response) => {
      return response.data;
    })
    .catch((err) => {
      console.log(err);
    });

  return await customerDetails;
}

function App() {
  const [existingCard, setExistingCard] = useState("");
  const [creditScore, setCreditScore] = useState(0);
  const [job, setJob] = useState("");
  const [isComplimentaryCard, setIsComplimentaryCard] = useState(false);
  const [cardConfidenceMap, setCardConfidenceMap] = useState({});
  const [purchaseExpenditureMap, setPurchaseExpenditureMap] = useState({});
  const [listOfCards, setListOfCards] = useState([]);

  const handleSubmit = (customerDetails) => {
    const customerID = customerDetails.customerID;
    getCustomerDetails(customerID).then((customerDetails) => {
      setExistingCard(customerDetails.existingCard);
      setCreditScore(customerDetails.creditScore);
      setJob(customerDetails.job);
      setIsComplimentaryCard(customerDetails.isComplimentaryCard);
      setCardConfidenceMap(customerDetails.cardConfidenceMap);
      setPurchaseExpenditureMap(customerDetails.purchaseExpenditureMap);
      setListOfCards(customerDetails.cards);
    });
  };

  return (
    <div class='root-container'>
      <div class="top-bar">
        <div class="customer-id">
          <SearchBar onSubmit={handleSubmit} />
        </div>
        <div class="credit-score">
          <div class="field-name">Credit Score</div>
          <div class="field-value">{creditScore}</div>
        </div>
        <div class="profession">
          <div class="field-name">Profession</div>
          <div class="field-value">{job}</div>
        </div>
        <div class="existing-card">
          <div class="field-name">Existing Card</div>
          <div class="field-value">{existingCard}</div>
        </div>
      </div>
      <div class="details">
        <div>
          <CardConfidence cardConfidenceMap={cardConfidenceMap} />
        </div>
        <div>
          <ShoppingPattern purchaseExpenditureMap={purchaseExpenditureMap} />
        </div>
        <div>
          <CardDetails
            cards={listOfCards}
            isComplimentaryCard={isComplimentaryCard}
          />
        </div>
      </div>
    </div>
  );
}

export default App;
