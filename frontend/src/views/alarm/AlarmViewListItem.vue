<template>
  <v-card id="AlarmViewListItem" class="flex-container space-between" style="cursor:pointer" @click="AlarmViewDetail">
    <div>
      <div v-if="AlarmViewarticle.read === true" style="float:left; margin-left: 60px;">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-envelope-open" viewBox="0 0 16 16">
          <path d="M8.47 1.318a1 1 0 0 0-.94 0l-6 3.2A1 1 0 0 0 1 5.4v.817l5.75 3.45L8 8.917l1.25.75L15 6.217V5.4a1 1 0 0 0-.53-.882l-6-3.2ZM15 7.383l-4.778 2.867L15 13.117V7.383Zm-.035 6.88L8 10.082l-6.965 4.18A1 1 0 0 0 2 15h12a1 1 0 0 0 .965-.738ZM1 13.116l4.778-2.867L1 7.383v5.734ZM7.059.435a2 2 0 0 1 1.882 0l6 3.2A2 2 0 0 1 16 5.4V14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V5.4a2 2 0 0 1 1.059-1.765l6-3.2Z"/>
        </svg>
      </div>
      <div v-else  style="float:left; margin-left: 60px;">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
          <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"/>
        </svg>
      </div>
    </div>
    <div>
      <div style="font-weight:bold; margin-bottom: 5px;">{{ AlarmViewarticle.title }}</div>
      <div style="font-weight:lighter; margin-bottom: 5px;">{{ AlarmViewarticle?.createdAt }}</div>
    </div>
    <div>
      <v-btn @click="AlarmViewarticleDelete" >알림 삭제</v-btn>
    </div>
  </v-card>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'AlarmViewListItem',
  props: {
    AlarmViewarticle: Object,
    index: Number,
    limit: Number,
  },
  data() {
    return {
      // read: false,
    }
  },
  methods: {
    AlarmViewDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification/${this.AlarmViewarticle.id}`,
        headers: { 
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        this.$swal(this.AlarmViewarticle.content);

        this.$swal.fire({
          title: this.AlarmViewarticle.title,
          html: this.AlarmViewarticle.content,
          showCloseButton: false,
          showCancelButton: false,
          focusConfirm: false
        })

        
        // 읽지 않은 알림이었다면, 알림수 - 1
        if(!this.AlarmViewarticle.read) {
          this.$store.dispatch("discountNoti");
        }
        this.$emit('refresh-alarm')
        // this.read=true
      })
      .catch((err) => {
        console.log('안됨')
      })
    },
    
    AlarmViewarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/my-page/notification/${this.AlarmViewarticle.id}`,
        headers: { 
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$emit('delete-alarm')
        // this.$router.push({ name: 'AlarmViewList' });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
  }
}
</script>

<style>
#AlarmViewListItem {
  display: flex;
  justify-content: space-between;
  width: 90%;
  height: 100px;
  margin-left: 3rem;
  margin-bottom: 1rem;
  align-items: center;
}
</style>
