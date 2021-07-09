import React, { useState } from "react"
import CustomerID from "./CustomerID"
import Recommendation from "./Recommendation"
// import Spending from "./Spending"

function App() {
    
    const [customerID, setCustomerID] = useState("1")
    const handleCustomerIDSubmit = (customerID) => {
        setCustomerID(customerID)
    }

    return (
        <div>
            <div>
                <CustomerID handleSubmit={handleCustomerIDSubmit} />
            </div>
            <div>
                <div>
                    <Recommendation customerID={customerID} />
                </div>
            </div>
        </div>
    )
}

export default App
