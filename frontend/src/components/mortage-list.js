import { useEffect, useState } from "react"
import service from "../service"
import "./mortage-list.css"

const MortageList = () => {
	const [data, setData] = useState(null)
	useEffect(() => {
		service.getList().then(r => setData(r))
	}, [])

	return(
		data ?
		<table className="mortage-table">
			<tbody>
			<tr>
					<th>
						Name
					</th>
					<th>
						Total (€)
					</th>
					<th>
						Interest (%)
					</th>
					<th>
						Monthly payment (€)
					</th>
				</tr>
				{data.map(m => 
				<tr>
					<td>
						{m.name}
					</td>
					<td>
						{m.loanSize}
					</td>
					<td>
						{m.interest}
					</td>
					<td>
						{m.monthlyPayment.toFixed(2)}
					</td>
				</tr>
				)}
			</tbody>
		</table> :
		<div>Loading...</div>
	)
}

export default MortageList;