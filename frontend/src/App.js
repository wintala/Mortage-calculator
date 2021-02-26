import { useState } from 'react';
import './App.css';
import Calculator from './components/calculator';
import MortageList from './components/mortage-list';

const App = () => {
  const [location, setLocation] = useState(true) //true => calc, false => mortage list


  return(
  <>
    <button className="loc-button" onClick={() => setLocation(l => !l)}>{location ? "Mortage list" : "Calculator"}</button>
    {location ? <Calculator /> : <MortageList />}
  </>
  )
}

export default App;
