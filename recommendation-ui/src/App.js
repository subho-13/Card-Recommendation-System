import axios from "axios";
import React, { useState } from "react";

import SearchBar from "./search_bar/SearchBar"
import CardDetails from "./card_details/CardDetails";

async function getCustomerDetails(customerID) {
  var customerDetails = await axios.get(`http://localhost:9508/get/${customerID}`)
          .then((response) => { 
                return response.data
              }).catch((err) => {
                console.log(err);
              })

 return await customerDetails
}

function App() {
  const [cardConfidenceMap, setCardConfidenceMap] = useState({})
  const [purchaseExpenditureMap, setPurchaseExpenditureMap] = useState({})

  const handleSubmit = (customerDetails) => {
    const customerID = customerDetails.customerID
    getCustomerDetails(customerID).then(customerDetails => {
      setCardConfidenceMap(customerDetails.cardConfidenceMap)
    })
  }

  return <div>
    <div>
      <SearchBar onSubmit={handleSubmit} />
    </div>
    <div>
      <div>
        <CardDetails cardConfidenceMap={cardConfidenceMap} />
      </div>
    </div>
  </div>
}

export default App;
