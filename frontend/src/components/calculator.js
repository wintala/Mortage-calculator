import React, { useState } from "react"
import service from "../service"
import "./calculator.css"

const Calculator = () => {
	const [name, setName] = useState("")
	const [size, setSize] = useState("")
	const [interest, setInterest] = useState("")
	const [time, tsetTime] = useState("")
	const [results, setResults] = useState(null)

	const handleCalculation = async (e) => {
		e.preventDefault()
		if (!size || !interest || !time || !name) {
			alert("All fields must be filled")
			return null
		}

		try {
			const res = await service.getData(name, size, interest / 100, time)
			setResults(res)
		} catch (e) {
			alert(`The following error occured: ${e}`)
		}
		
	}

	const objectToTable = (o) => {
		const keysToText = {
			years: "Years",
			loanSize: "Loan size (€)",
			payments: "Payments",
			interest: "Interest rate (%)",
			monthlyInterest: "Monthly interest rate (%)",
			monthlyPayment: "Monthly payment (€)",
			name: "Name"
		}

		const handleNumRound = (value, key) => {
			if (key === "monthlyInterest" || key === "interest" || key === "monthlyPayment") {
				return (value * 100).toFixed(2)
			} else if (key === "name") {
				return value
			}
			return Math.round((value + Number.EPSILON) * 100) / 100
		}
		return(
			<table>
				<tbody>
				{Object.keys(o).map((k) =>
					<tr key={k}>
						<td>{keysToText[k]}</td>
						<td>{handleNumRound(o[k], k)}</td>
					</tr>
				)
				}
				</tbody>
			</table>
		)
	}


	return (
		<div id="calculator">
			<h1>Mortage calculator</h1>
			<form onSubmit={handleCalculation}>
				<div>
					<p>Your name</p>
					<div>
						<input
							type="text"
							value={name}
							onChange={({ target }) => setName(target.value)}
						/>
					</div>
				</div>
				<div>
					<p>Loan size (€)</p>
					<div>
						<input
							type="number"
							step="0.01"
							max="1000000000000"
							min="0"
							value={size}
							onChange={({ target }) => setSize(target.value)}
						/>
					</div>
				</div>
				<div>
					<p>Interest (%)</p>
					<div>
						<input
							type="number"
							step="0.01"
							max="1000"
							min="0"
							value={interest}
							onChange={({ target }) => setInterest(target.value)}
						/>
					</div>
				</div>
				<div>
					<p>Payment time (years)</p>
					<div>
						<input
							type="number"
							min="0"
							max="1000"
							value={time}
							onChange={({ target }) => tsetTime(target.value)}
						/>
					</div>
				</div>
				<button>Calculate</button>
			</form>
			{results ?
			<div>
				{objectToTable(results)}
			</div> :
			null}
		</div>
	)
}

export default Calculator