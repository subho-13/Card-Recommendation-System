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

    return <form className='search-form' onSubmit={handleSubmit}>
            <button type="submit" className='submit-button' >Customer ID</button>
            <input 
                type="text" 
                name="customerID" 
                value={customerID} 
                onChange={handleChange}  
                onClick={handleClick}
                className='input-bar'
            />
        </form>
}

export default SearchBar