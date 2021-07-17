import React, { useState } from 'react'

function SearchBar(props) {
    const [customerID, setCustomerID] = useState("1");
    
    const handleChange = (event) => {
        setCustomerID(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()
        props.onSubmit({
            "customerID" :customerID
        })
    }

    const handleClick = (event) => {
        setCustomerID("")
    }

    return <div>
        <form onSubmit={handleSubmit}>
            <input 
                type="text" 
                name="customerID" 
                value={customerID} 
                onChange={handleChange}  
                onClick={handleClick}
            />
            <button type="submit">Fetch</button>
        </form>
    </div>
}

export default SearchBar