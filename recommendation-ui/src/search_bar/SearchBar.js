import React, { useState } from 'react'
import './SearchBar.css'

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

    return <form class='search-bar' onSubmit={handleSubmit}>
            <button type="submit" class='submit-button' >Customer ID</button>
            <input 
                type="text" 
                name="customerID" 
                value={customerID} 
                onChange={handleChange}  
                onClick={handleClick}
                class='input-bar'
            />
        </form>
}

export default SearchBar