<template>
  <div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 work-item">
    <div class="card">
      <div>
        <img v-if="counselor.profileImg != null" v-bind:src="`${counselor.profileImg}`" alt="까비" class="card_image profileImg">
        <img class="profileImg" v-else v-bind:src="`${this.nullProfileImg}`" alt="까비" >
      </div>
      <div class="card__overlay">
        <div class="card__header">
          <svg class="card__arc" xmlns="http://www.w3.org/2000/svg"><path /></svg>
          <div class="card__header-text">
            <h2 class="card__title">{{ counselor.name}} 상담사</h2>
            <div class="starRate">
              <v-rating
                model-value="3"
                size="x-small"
                density="comfortable"
                color="yellow"
              ></v-rating>
              <!-- <span class="text-grey-lighten-2 text-caption me-2">({{ 230 }})</span> -->
            </div>
            <div class="card__status">{{ convertConsultType(counselor.consultTypeList) }}</div>
          </div>
        </div>
        <div class="card__description">
          <div>{{ counselor.introduce }}</div>

          <button class="icon-btn add-btn">
            <div class="add-icon"></div>
            <div class="btn-txt" @click="moveTo(counselor.id)">자세히 보기</div>
          </button>
        </div>
      </div>
    </div>
  </div>
  <!-- <div class="col-md-4 col-sm-6 col-xs-6 col-xxs-12 work-item">
    <v-card>
    <v-row>
      <v-col>
        <v-card
        color="white"
        theme="dark"
        >
        <v-avatar
          class="ma-3"
          size="125"
          rounded="0"
        >
        <img v-bind:src="`${counselor.profileImg}`" alt="까비" style="width:100%">
          
        </v-avatar>
        <div class="d-flex flex-no-wrap justify-space-between">
          <div>
            <v-card-title class="text-h5">
              {{ counselor.name}}
            </v-card-title>
            
            <v-card-subtitle>
              {{ counselor.introduce }}
              <br>
              {{ counselor.consultTypeList}}
            </v-card-subtitle>
            <v-card-actions>
              <v-btn
                outlined
                rounded
                text
                @click="moveTo(counselor.id)"
              >
                Button
              </v-btn>
            </v-card-actions>
          </div>
        </div>
      </v-card>
    </v-col>
  </v-row>
</v-card>
</div> -->
</template>

<script>

// import {useRouter} from 'vue-router';

export default {
  name: 'ConsultantCard',
  data() {
    return {
      nullProfileImg : require('../../assets/images/사람altimg.png'),
    }
  },
  props: {
    counselor: Object,
  },
  methods:{
    moveTo(){
      this.$router.push({ name: 'consultantcarditem', params: {id: this.counselor.id} })
    },
    convertConsultType(list) {
      const selectTypeList = [
        {name:'#아동 #청소년', value:"CHILD_TEENAGER"},
        {name:'#재난', value:"CALAMITY"},
        {name:'#부부 #가족상담', value:"COUPLE_FAMILY"},
        {name:'#재활', value:"REHABILITATION"},
        {name:'#노인', value:"AGED"},
        {name:'#중독', value:"ADDICTED"},
        {name:'#정신', value:"MENTAL_HEALTH"},
        {name:'#교정', value:"CORRECTION"},
        {name:'#진로', value:"COURSE"},
        {name:'#상담자교육', value:"EDUCATION"},
        {name:'#성폭력', value:"SEXUAL_VIOLENCY"},
        {name:'#상담자슈퍼비전', value:"SUPERVISION"},
        {name:'#스포츠상담', value:"SPORTS"},
        {name:'#학교상담', value:"SCHOOL"},
      ];

      let result = "";
      list.forEach(element => {
          selectTypeList.forEach( type => {
              if(type.value === element) {
                  result += type.name + " ";
                  return;
              }
          })
      });
      return result;
    }
  }
}
</script>

<style>
:root {
  --surface-color: #fff;
  --curve: 40;
}

* {
  box-sizing: border-box;
}

body {
  font-family: 'Noto Sans JP', sans-serif;
  background-color: #fef8f8;
}

.cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  margin: 4rem 5vw;
  padding: 0;
  list-style-type: none;
}

.card {
  position: relative;
  display: block;
  height: 80%;  
  border-radius: calc(var(--curve) * 1px);
  overflow: hidden;
  text-decoration: none;
}

.card__image {      
  width: 100%;
  height: auto;
  object-fit: cover;
}

.card__overlay {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1;      
  border-radius: calc(var(--curve) * 1px);    
  background-color: var(--surface-color);      
  transform: translateY(100%);
  transition: .2s ease-in-out;
}

.card:hover .card__overlay {
  transform: translateY(0);
}

.card__header {
  position: relative;
  display: flex;
  align-items: center;
  gap: 2em;
  padding: 2em;
  border-radius: calc(var(--curve) * 1px) 0 0 0;    
  background-color: var(--surface-color);
  transform: translateY(-100%);
  transition: .2s ease-in-out;
}

.card__arc {
  width: 80px;
  height: 80px;
  position: absolute;
  bottom: 100%;
  right: 0;      
  z-index: 1;
}

.card__arc path {
  fill: var(--surface-color);
  d: path("M 40 80 c 22 0 40 -22 40 -40 v 40 Z");
}       

.card:hover .card__header {
  transform: translateY(0);
}

.card__thumb {
  flex-shrink: 0;
  width: 50px;
  height: 50px;      
  border-radius: 50%;      
}

.card__title {
  font-size: 1.7em;
  margin: 0 0 .2em;
  color: #6A515E;
}

.card__tagline {
  display: block;
  margin: 1em 0;
  font-family: "MockFlowFont";  
  font-size: 1em; 
  color: #D7BDCA;  
}

.card__status {
  font-size: .8em;
  color: #D7BDCA;
}

.card__description {
  padding: 0 2em 2em;
  margin: 0;
  color: #D7BDCA;
  font-family: "MockFlowFont";   
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}    

.main-hr {
  width: 30%;
  border: none;
  border-top: 1px solid #c3c3c3;
}
.icon-btn {
  width: 50px;
  height: 50px;
  border: 1px solid #cdcdcd;
  background: white;
  border-radius: 25px;
  overflow: hidden;
  position: relative;
  transition: width 0.2s ease-in-out;
}
.add-btn:hover {
  width: 120px;
}
.add-btn::before,
.add-btn::after {
  transition: width 0.2s ease-in-out, border-radius 0.2s ease-in-out;
  content: "";
  position: absolute;
  height: 4px;
  width: 10px;
  top: calc(50% - 2px);
  background: black;
}
.add-btn::after {
  right: 14px;
  overflow: hidden;
  border-top-right-radius: 2px;
  border-bottom-right-radius: 2px;
}
.add-btn::before {
  left: 14px;
  border-top-left-radius: 2px;
  border-bottom-left-radius: 2px;
}
.icon-btn:focus {
  outline: none;
}
.btn-txt {
  opacity: 0;
  transition: opacity 0.2s;
}
.add-btn:hover::before,
.add-btn:hover::after {
  width: 4px;
  border-radius: 2px;
}
.add-btn:hover .btn-txt {
  opacity: 1;
}
.add-icon::after,
.add-icon::before {
  transition: all 0.2s ease-in-out;
  content: "";
  position: absolute;
  height: 20px;
  width: 2px;
  top: calc(50% - 10px);
  background: black;
  overflow: hidden;
}
.add-icon::before {
  left: 22px;
  border-top-left-radius: 2px;
  border-bottom-left-radius: 2px;
}
.add-icon::after {
  right: 22px;
  border-top-right-radius: 2px;
  border-bottom-right-radius: 2px;
}
.add-btn:hover .add-icon::before {
  left: 15px;
  height: 4px;
  top: calc(50% - 2px);
}
.add-btn:hover .add-icon::after {
  right: 15px;
  height: 4px;
  top: calc(50% - 2px);
}
.icon-btn.add-btn {
  float: right;
}
.starRate {
  display: flex;
  align-items: center;
  margin-bottom: 0.8rem;
}
.profileImg {
  width:100%;
  height: 370px;
  object-fit: cover;
}

</style>
