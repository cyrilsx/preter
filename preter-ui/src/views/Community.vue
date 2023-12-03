<script setup>
import data from '@/data.json';

const communites = data.communities;
const messages = [
  {
    from: 'You',
    message: `Sure, I'll see you later.`,
    time: '10:42am',
    color: 'deep-purple-lighten-1',
  },
  {
    from: 'John Doe',
    message: 'Yeah, sure. Does 1:00pm work?',
    time: '10:37am',
    color: 'green',
  },
  {
    from: 'You',
    message: 'Did you still want to grab lunch today?',
    time: '9:47am',
    color: 'deep-purple-lighten-1',
  },
];

</script>

<template>
  <div class="d-flex align-center justify-center">
  <v-list>
    <v-list-item>
    <h2 class="flex-fill">My communities</h2>
    <v-divider />
    </v-list-item>
    <v-list-item>
    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="community in communites"
          :key="community.slug"
          :cols="community.flex"
        >
          <v-card>
            <v-img
              :src="community.image"
              class="align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="200px"
              cover
            >
              <v-card-title class="text-white" v-text="community.name"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn size="small" color="surface-variant" variant="text" icon="mdi-heart"></v-btn>

              <v-btn size="small" color="surface-variant" variant="text" icon="fas fa-walking"></v-btn>

              <v-btn size="small" color="surface-variant" variant="text" icon="fas fa-door-open"></v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    </v-list-item>
  <v-divider />
    <v-list-item>
    <h2 class="flex-fill">Latest news</h2>
    </v-list-item>
    <v-divider />
    <v-list-item>
      <div class="font-weight-bold ms-1 mb-2">
        Today
      </div>

      <v-timeline density="compact" align="start">
        <v-timeline-item
          v-for="message in messages"
          :key="message.time"
          :dot-color="message.color"
          size="x-small"
        >
          <div class="mb-4">
            <div class="font-weight-normal">
              <strong>{{ message.from }}</strong> @{{ message.time }}
            </div>
            <div>{{ message.message }}</div>
          </div>
        </v-timeline-item>
      </v-timeline>
    </v-list-item>
  </v-list>
<!--    <h2>Community</h2>-->

<!--  <div v-for="community in communites" :key="community.name">-->
<!--    <h3>{{ community.name }}</h3>-->
<!--    <p>{{ community.description }}</p>-->
<!--    <v-img :src="community.image" :alt="community.name" width="200"></v-img>-->
<!--  </div>-->
  </div>
</template>

<style scoped>

</style>
