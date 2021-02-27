import axios from "axios"

const baseUrl = 'http://localhost:4567'

const getData = async (name, loanSize, interest, years) => {
  const config = {
    params: {name, loanSize, interest, years},
  }

  return axios.post(`${baseUrl}/api/mortagecalculator`, null, config).then(response => response.data)
}

const getList = async () => {

  return axios.get(`${baseUrl}/api/mortages`).then(response => response.data)
}

const exports = {getData, getList}

export default exports