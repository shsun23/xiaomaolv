<template>
  <section class="lb-video-wrap">
    <video-player
      ref="videoPlayer"
      class="video-player vjs-custom-skin"
      :playsinline="true"
      :options="playerOptions"
      @pause="onPlayerPause($event)"
    />
  </section>
</template>

<script>
// import videoC from '@/views/table/dynamic-table/components/test5'
export default {
  props: {
    url: {
      type: String,
      // default: 'https://www.gejiba.com/view.php/d7e6abdd4818e56c8f80dc77b96d4b6f.mp4',
      default: this.$store.state.stateVideoUrl
      // data: this.$store.state.stateVideoUrl // 获取状态管理里的视频地址
    }
  },
  data() {
    return {
      playerOptions: {}
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      this.playerOptions = {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: false, // 如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        // flash:{hls:{withCredentials:false}},
        // html5:{hls:{withCredentials:false}},
        sources: [{
          type: '',
          src: this.url// url地址
        }],
        poster: 'https://www.gejiba.com/view.php/bf21c0faf45272632293842f88462c40.jpg', // 你的封面地址
        // width: document.documentElement.clientWidth,
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true // 全屏按钮
        }
      }
    },
    // 监听暂停事件
    onPlayerPause(player) {
      console.log(player)
    }
  }
}
</script>

<style lang="scss">
.lb-video-wrap{
//   height: 100%;
//   width:100%;
  height: 100%;
  width:100%;
//   .video-player-list,.video-player,.video-js{
//     height: 100%;
//     width: 100%;

//   }
    .video-player-list,.video-player,.video-js{
    height: 100%;
    width: 100%;

  }
//   .vjs-fluid{
//     padding-top: 0px;
//   }
    .vjs-fluid{
    padding-top: 500px;
  }
  .video-js{
    .vjs-big-play-button{
      width:50px!important;
      height: 50px!important;
      line-height:50px!important;
      margin:0!important;
      transform: translate(-50%,-50%);
      border-radius: 50px!important;
      span{
        line-height:50px;
      }
      .vjs-icon-placeholder{
        font-size: 30px;
      }
    }
  }
}
</style>
