<template>
<div class="container">
  <div class=" text-center mt-5 ">
    <h1 >상담 신청서</h1>      
  </div>
  <div class="row ">
    <div class="col-lg-7 mx-auto">
      <div class="card mt-2 mx-auto p-4 bg-light">
        <div class="card-body bg-light">
          <div class = "container">
            <form id="contact-form" role="form">
              <div class="controls">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="form_need" @click="checkDate(dueDate)">상담 유형 *</label>
                      <select id="form_need" class="form-control" v-model="consultType" required="required">
                        <option 
                          v-for="(item, index) in selectTypeList"
                          :key="index"
                          :value="item.value"
                          >{{ item.name }}</option
                        >
                      </select>
                      <label for="form_name">성함 *</label>
                      <input id="form_name" type="text" name="name" v-model="name" class="form-control" placeholder="성함" required="required" data-error="이름은 필수사항입니다">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label for="form_lastname">성별 *</label>
                        <select id="form_need" v-model="gender" class="form-control">
                          <option 
                            v-for="(item, index) in genderList"
                            :key="index"
                            :value="item.value"
                            >{{ item.name }}</option
                          >
                        </select>
                        <label for="form_name">나이 *</label>
                        <input id="form_name" type="number" name="name" v-model="age" class="form-control" placeholder="나이" required="required" data-error="이름은 필수사항입니다">


                    </div>
                  </div>
                </div>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">


                    <label for="form_email">Email *</label>
                    <input id="form_email" type="email" name="email"  v-model="email" class="form-control" placeholder="E-mail" required="required" data-error="Valid email is required.">
                  </div>
                </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label for="form_name">TEL</label>
                      <input id="form_name" type="number" name="name" class="form-control" v-model="tel" placeholder="TEL" required="required" data-error="Firstname is required.">
                  </div>
                </div>
              </div>
              <div>
                <label for="form_name">상담 기한</label>
                <datepicker
                 class="form-control"
                 placeholder="YYYY-MM-DD" required="required"
                  v-model="dueDate"
                  lang="ko"
                  :lowerLimit="new Date()"
                  :clearable="false"
                />

              </div>

            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label for="form_message">고민 내용 *</label>
                    <textarea id="form_message" name="message" v-model="content" class="form-control" placeholder="원하시는 상담" rows="4" required="required" data-error="Please, leave us a message."></textarea>
                </div>
              </div>
              <div class="col-md-12 d-flex justify-content-center" style="margin:auto;">
                <input type="submit" class="btn btn-success btn-send  pt-2 btn-block" value="Send Message" @click="reserveConsult">
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
        <!-- /.8 -->
    </div>
    <!-- /.row-->
</div>
</div>

</template>


<script>
// import Datepicker from 'vue3-datepicker';

import axios from 'axios'
import Datepicker from 'vue3-datepicker'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'ConsultantCardItemReserve',
  components:{
    // DateTimePicker
    Datepicker
  },
  props:{
    counselorData:Object
  },  
  data(){
    return{
      age:null,
      clientId: this.$store.state.payload.id,
      consultType:null,
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
      content:null,
      counselorId: this.counselorData.id,
      dueDate:null,
      email:null,
      gender:null,
      genderList:[
        {name:'남', value:"MEN"},
        {name:'여', value:"WOMEN"},
      ],
      name:null,
      state:'WAIT',
      tel:null,
    }
  },
  methods:{
    // updateDate(){
    //   this.date=value;
    // },
    dateFormat(dueDate){
      let month = dueDate.getMonth() + 1;
      let day = dueDate.getDate();
      let hour = dueDate.getHours();
      let minute = dueDate.getMinutes();
      let second = dueDate.getSeconds();

      month = month >= 10 ? month : '0' + month;
      day = day >= 10 ? day : '0' + day;
      hour = hour >= 10 ? hour : '0' + hour;
      minute = minute >= 10 ? minute : '0' + minute;
      second = second >= 10 ? second : '0' + second;

      return dueDate.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    },
    checkDate(dueDate){
      // console.log(this.$store.state.token.[[Target]])
      // const clientId=clientId
      console.log(this.age,this.clientId,this.consultType,this.content,this.counselorData.id)
      console.log(this.dateFormat(dueDate),this.email,this.gender,this.name,this.state,this.tel)
    },
    reserveConsult(){
      axios({
        method:'POST',
        url: `${VUE_APP_API_URL}/api/consultApplicant`,
        data:{
          age: this.age,
          clientId: this.clientId,
          consultType: this.consultType,
          content: this.content,
          counselorId: this.counselorData.id,
          dueDate: this.dueDate,
          email: this.email,
          gender: this.gender,
          name: this.name,
          state: this.state,
          tel: this.tel
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
          console.log(res)
        //   this.$router.push({name:'consultantcarditem'})
        })
    },
  },

}
</script>



<style>
body {
    font-family: 'Lato', sans-serif;
}

h1 {
    margin-bottom: 40px;
}

label {
    color: #333;
}

.btn-send {
    font-weight: 300;
    text-transform: uppercase;
    letter-spacing: 0.2em;
    width: 80%;
    margin-left: 3px;
    }
.help-block.with-errors {
    color: #ff5050;
    margin-top: 5px;

}

.card{
	margin-left: 10px;
	margin-right: 10px;
}

</style>