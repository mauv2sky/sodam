<template>
    <v-container id="fh5co-main-AlarmViewList">
      <div class="fh5co-narrow-content">
        <div class="Card">
          <v-tabs
            v-model="tab"
            bg-color="transparent"
            color="grey"
            grow
          >
            <v-tab value="one">
              전체
            </v-tab>
            <v-tab value="two">
              상담
            </v-tab>
            <v-tab value="three">
              고민 게시판
            </v-tab>
            <v-tab value="four">
              HELP DESK
            </v-tab>
          </v-tabs>
          
          <v-window v-model="tab">
            <v-window-item value="one">
              <AlarmViewListAll class="alarmListArea" />
            </v-window-item>
            
            <v-window-item value="two">
              <AlarmViewListSangdam class="alarmListArea" />
            </v-window-item>
            
            <v-window-item value="three">
              <AlarmViewListBoard class="alarmListArea" />
            </v-window-item>
            
            <v-window-item value="four">
              <AlarmViewListHelp class="alarmListArea" />
            </v-window-item>
          </v-window>
        </div>
      </div>
  </v-container>
</template>
  
<script>
import AlarmViewListAll from '../alarm/AlarmViewListAll.vue'
import AlarmViewListSangdam from '../alarm/AlarmViewListSangdam.vue'
import AlarmViewListBoard from '../alarm/AlarmViewListBoard.vue'
import AlarmViewListHelp from '../alarm/AlarmViewListHelp.vue'

  export default {
    name: 'AlarmViewList',
    components: {
      AlarmViewListAll,
      AlarmViewListSangdam,
      AlarmViewListBoard,
      AlarmViewListHelp,
    },
    data(){
      return{
        tab:null
      }
    },

  }
  </script>
  
  
<style >
 #fh5co-main-AlarmViewList {
  width: 85%;
  float: right;
  margin-right: 2rem;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 4em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
}
.Card {
  margin: 3rem;
}
.alarmListArea {
  margin-top: 3rem;
  width: 100%;
}
</style>