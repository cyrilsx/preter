<template>
  <div class="result">
    <div class="row">
      <article-loading/>
      <article-loading/>
      <article-loading/>
      <article-loading/>
    </div>
  </div>
</template>

<script>
import ArticleLoading from '@/components/ArticleLoading.vue'
import remote from '@/services/remote'

export default {
  name: 'ArticleList',
  components: {
    ArticleLoading
  },
  mounted () {
    remote.searchArticle()
  }
}
</script>

<style scoped>
  .result {
    margin: 10px;
  }

</style>
