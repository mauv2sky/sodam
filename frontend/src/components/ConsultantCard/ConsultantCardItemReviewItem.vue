<template>
  <div class="reviewListArea">
    <div class="reviewItem">
      <v-card>
        <v-card-title>{{ review.title }}</v-card-title>
        <v-card-subtitle>
          <v-rating
            v-bind:model-value=rating
            size="x-small"
            density="comfortable"
            color="orange"
          ></v-rating>
        </v-card-subtitle>
        <v-card-text>{{ review.contents }}</v-card-text>
        <v-card-text style="color:gray">{{ convertDate }} | {{ convertType }} ({{ convertPastCount }}) | {{ convertClientId }}</v-card-text>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name:'ConsultantCardItemReviewItem',
  props:{
    review:Object,
  },
  data() {
    return {
      rating: this.review.stars,
      selectTypeList: [
        {name:'아동 청소년', value:"CHILD_TEENAGER"},
        {name:'재난', value:"CALAMITY"},
        {name:'부부 및 가족상담', value:"COUPLE_FAMILY"},
        {name:'재활 상담', value:"REHABILITATION"},
        {name:'노인 상담', value:"AGED"},
        {name:'중독 상담', value:"ADDICTED"},
        {name:'정신 건강', value:"MENTAL_HEALTH"},
        {name:'교정 상담', value:"CORRECTION"},
        {name:'진로 상담', value:"COURSE"},
        {name:'상담자 교육', value:"EDUCATION"},
        {name:'성폭력 상담', value:"SEXUAL_VIOLENCY"},
        {name:'상담자 슈퍼비전', value:"SUPERVISION"},
        {name:'스포츠 상담', value:"SPORTS"},
        {name:'학교 상담', value:"SCHOOL"},
      ],
    }
  },
  methods:{
    goDetail(){
      this.$router.push({name:'ConsultantCardItemReviewItemDetail', params:{id: this.review.id}})
    },
  },
  computed: {
    getRating() {
      return this.review.stars
    },
    convertDate() {
      return this.review.createdAt.split('T')[0];
    },
    convertPastCount() {
      if(this.review.pastCount === 0) return '초기상담'
      else return `${this.review.pastCount} 번 상담`
    },
    convertType() {
      let result = ""
      this.selectTypeList.forEach(e => {
        if(this.review.type === e.value) {
          result = e.name
        }
      })
      return result
    },
    convertClientId() {
      let prevId = this.review.clientId;
      let currId = prevId.substr(0, 5) + '*'.repeat(prevId.length - 5);
      return currId;
    }
  }

}
</script>

<style>
#fh5co-main2 {

  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main2 {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main2 {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 1em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main2 .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
}
.reviewListArea {
  margin: 0 2rem;
}
.ReviewContainer{
  padding: 20px;
  border-bottom: solid 1px #ccc;
}
.reviewItem {
  margin: 0 3rem 2rem 3rem;
}
</style>