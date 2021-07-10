import React, {useState} from "react"
import './App.css';
import Purchase from './purchaseExpMap/Purchase';

const App = () => {
  const [customerID, setCustomerID] = useState(-1);
  const [flagSubmit, setFlagSubmit] = useState(false);

  const handleChange = (e) => {
      setFlagSubmit(false);
      setCustomerID(e.target.value);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    setFlagSubmit(true);
  }

  return (
    <div className="App">     
          <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="customerID"
                value={customerID}
                onChange={handleChange}                
            />
            <button type="submit">Fetch</button>
        </form>      
          {flagSubmit &&  <Purchase customerID={customerID} />}
    </div>
  );
}

export default App;
