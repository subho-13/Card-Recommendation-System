import React, { useState } from "react"

function CustomerID(props) {
    const [customerID, setCustomerId] = useState("Customer ID")

    const handleSubmit = (event) => {
        event.preventDefault()
        props.handleSubmit(customerID)
    }

    const onChange = (event) => {
        setCustomerId(event.target.value)
    }

    const onClick = (event) => {
        setCustomerId("")
    }

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="customerID"
                value={customerID}
                onChange={onChange}
                onClick={onClick}
            />
            <button type="submit">Fetch</button>
        </form>
    )
}

export default CustomerID
