<template>
  <!-- <div>
    <a id="custom-login-btn" @click="kakaoLogin()">
      <img
      src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg"
      width="222"
      alt="카카오 로그인 버튼"
      />
    </a>
    <div @click="kakaoLogout()">로그아웃</div>
  </div> -->
  <!-- <div>
    <button class="background2" @click="loginWithKakao"></button>
  </div> -->

  <a id="loginBtn" type="button" onclick="function login() {
    const redirectUri = 'VUE_APP_API_URL/auth/kakao/callback';
        const appKey = '9e0f9a70f672fba12b71ea1b5ec10e80'
            window.location.href = 'https://kauth.kakao.com/oauth/authorize?client_id=' + appKey +
    '&redirect_uri=' + redirectUri + '&response_type=code';}
    login()">
  <img class="background2"></a>


  <!-- <a onclick="alert('alert test')">alert?</a> -->
</template>

<script>
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  methods: {
    kakaoLogin() {
      window.Kakao.Auth.login({
        scope: "profile_nickname",
        success: this.getKakaoAccount,
      });
    },
    getKakaoAccount() {
      window.Kakao.API.request({
        url: '/v2/user/me',
        success: (res) => {
          const kakao_account = res.kakao_account;
          const ninkname = kakao_account.profile.ninkname;
          const email = kakao_account.email;
          console.log("ninkname", ninkname);
          console.log("email", email);

          //로그인처리구현

          alert("로그인 성공!");
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    kakaoLogout() {
      window.Kakao.Auth.logout((res) => {
        console.log(res);
      });
    },
  },
};
// export default {
//     name: "LoginKakao",
//     methods: {
//         loginWithKakao() {
//           console.log('가나다라')
//           const params = {
//               redirectUri: "http://localhost:80",
//           };
//           window.Kakao.Auth.authorize(params);
//         },
//     },
// };
</script>

<style>
.background2 {
  width:300px;
  height:40px;
  background-image: url(../../../assets/images/kakao_login_btn.png);
}
</style>