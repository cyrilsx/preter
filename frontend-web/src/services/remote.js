import axios from 'axios'

export default {

  searchArticle () {
    axios.get('article')
      .then(function (response) {
        console.log(response)
        return response
      })
      .catch(function (error) {
        console.log(error)
      })
  },


  searchArticleByCategory (cat) {
    // axios.get(this.serverUrl + '/article/' + cat)
  }
}
