<script setup>
const objects = [
  {
    id: "1",
    name: "Kettle",
    description: "A kettle",
    image: "https://www.ikea.com/ch/en/images/products/vattentaet-kettle-stainless-steel-black__0713344_pe729450_s5.jpg?f=s",
    category: "Kitchen",
    owner: "1",
  },
  {
    id: "2",
    name: "Kettle",
    description: "A kettle",
    image: "https://www.ikea.com/ch/en/images/products/vattentaet-kettle-stainless-steel-black__0713344_pe729450_s5.jpg?f=s",
    category: "Kitchen",
    owner: "1",
  },
  {
    id: "3",
    name: "Kettle",
    description: "A kettle",
    image: "https://www.ikea.com/ch/en/images/products/vattentaet-kettle-stainless-steel-black__0713344_pe729450_s5.jpg?f=s",
    category: "Kitchen",
    owner: "1",
  }

]
</script>

<template>
  <v-list lines="three">
    <v-list-item
      v-for="obj in objects"
      :key="obj.id"
      :title="obj.name"
      :subtitle="obj.description"
    ></v-list-item>
  </v-list>
</template>

<style scoped>

</style>
