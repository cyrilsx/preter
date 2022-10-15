<template>
  <va-card class="flex md2 col-first">
    <va-card-title><span class="blur">Article</span></va-card-title>
    <va-inner-loading loading>
      <va-card-content class="blur">
        Whether article spirits new her covered hastily sitting her. Money witty books nor son add. Chicken age had evening believe but proceed pretend mrs. At missed advice my it no sister. Miss told ham dull knew see she spot near can. Spirit her entire her called.
      </va-card-content>
    </va-inner-loading>
  </va-card>
</template>

<script>
export default {
  name: 'ArticleLoading'
}
</script>

<style scoped>
.blur {
  color: transparent;
  text-shadow: 0 0 5px rgba(0,0,0,0.5);
}
</style>
