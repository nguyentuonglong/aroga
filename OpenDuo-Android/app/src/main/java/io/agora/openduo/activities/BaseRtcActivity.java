package io.agora.openduo.activities;

import android.util.Log;
import android.view.SurfaceView;

import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

public abstract class BaseRtcActivity extends BaseActivity {
    protected void joinRtcChannel(String token, String channel, String info, int uid) {
        rtcEngine().joinChannel(token, channel, info, uid);
    }

    protected void leaveChannel() {
        rtcEngine().leaveChannel();
    }

    protected void setVideoConfiguration() {
        rtcEngine().setVideoEncoderConfiguration(
                new VideoEncoderConfiguration(
                        config().getDimension(),
                        config().getFrameRate(),
                        VideoEncoderConfiguration.STANDARD_BITRATE,
                        config().getOrientation())
        );
    }

    protected SurfaceView setupVideo(int uid, boolean local) {
        SurfaceView surfaceView = RtcEngine.
                CreateRendererView(getApplicationContext());
        if (local) {
            rtcEngine().setupLocalVideo(new VideoCanvas(surfaceView,
                    VideoCanvas.RENDER_MODE_HIDDEN, uid));
        } else {
            rtcEngine().setupRemoteVideo(new VideoCanvas(surfaceView,
                    VideoCanvas.RENDER_MODE_HIDDEN, uid));
        }

        return surfaceView;
    }

    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
        Log.i("BaseRtcActivity", "onJoinChannelSuccess");
    }

    @Override
    public void onUserJoined(int uid, int elapsed) {
        Log.i("BaseRtcActivity", "onUserJoined");
    }

    @Override
    public void onUserOffline(int uid, int reason) {
        Log.i("BaseRtcActivity", "onUserOffline");
    }
}
