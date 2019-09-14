package com.bokecc.cloud.wowza.entity;

import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.config.RecordJsonConfig;
import com.bokecc.cloud.wowza.constant.RecordJsonConst;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 录制请求json体实体类
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class RecordJson {

    private String instanceName;
    private String fileVersionDelegateName;
    private String serverName;
    private Long currentSize;
    private String segmentSchedule;
    private boolean startOnKeyFrame;
    private String outputPath;
    private String currentFile;
    private String[] saveFieldList;
    private boolean recordData;
    private String applicationName;
    private boolean moveFirstVideoFrameToZero;
    private String recorderErrorString;
    private Long segmentSize;
    private boolean defaultRecorder;
    private boolean splitOnTcDiscontinuity;
    private String version;
    private String baseFile;
    private Long segmentDuration;
    private String recordingStartTime;
    private String fileTemplate;
    private Integer backBufferTime;
    private String segmentationType;
    private Long currentDuration;
    private String fileFormat;
    private String recorderState;
    private String option;

    public RecordJson() {
    }

    public RecordJson(Builder builder) {
        this.instanceName = builder.instanceName;
        this.fileVersionDelegateName = builder.fileVersionDelegateName;
        this.serverName = builder.serverName;
        this.currentSize = builder.currentSize;
        this.segmentSchedule = builder.segmentSchedule;
        this.startOnKeyFrame = builder.startOnKeyFrame;
        this.outputPath = builder.outputPath;
        this.currentFile = builder.currentFile;
        this.saveFieldList = builder.saveFieldList;
        this.recordData = builder.recordData;
        this.applicationName = builder.applicationName;
        this.moveFirstVideoFrameToZero = builder.moveFirstVideoFrameToZero;
        this.recorderErrorString = builder.recorderErrorString;
        this.segmentSize = builder.segmentSize;
        this.defaultRecorder = builder.defaultRecorder;
        this.splitOnTcDiscontinuity = builder.splitOnTcDiscontinuity;
        this.version = builder.version;
        this.baseFile = builder.baseFile;
        this.segmentDuration = builder.segmentDuration;
        this.recordingStartTime = builder.recordingStartTime;
        this.fileTemplate = builder.fileTemplate;
        this.backBufferTime = builder.backBufferTime;
        this.segmentationType = builder.segmentationType;
        this.currentDuration = builder.currentDuration;
        this.fileFormat = builder.fileFormat;
        this.recorderState = builder.recorderState;
        this.option = builder.option;
    }

    public static class Builder {
        private String instanceName;
        private String fileVersionDelegateName;
        private String serverName;
        private Long currentSize;
        private String segmentSchedule;
        private boolean startOnKeyFrame;
        private String outputPath;
        private String currentFile;
        private String[] saveFieldList;
        private boolean recordData;
        private String applicationName;
        private boolean moveFirstVideoFrameToZero;
        private String recorderErrorString;
        private Long segmentSize;
        private boolean defaultRecorder;
        private boolean splitOnTcDiscontinuity;
        private String version;
        private String baseFile;
        private Long segmentDuration;
        private String recordingStartTime;
        private String fileTemplate;
        private Integer backBufferTime;
        private String segmentationType;
        private Long currentDuration;
        private String fileFormat;
        private String recorderState;
        private String option;

        public Builder() {
            this.currentSize = Long.parseLong(RecordJsonConfig.getValue(RecordJsonConst.CURRENT_SIZE));
            this.startOnKeyFrame = Boolean.parseBoolean(RecordJsonConfig.getValue(RecordJsonConst.START_ON_KEY_FRAME));
            this.outputPath = RecordJsonConfig.getValue(RecordJsonConst.OUT_PUT_PATH);
            this.recordData = Boolean.parseBoolean(RecordJsonConfig.getValue(RecordJsonConst.RECORD_DATA));
            this.moveFirstVideoFrameToZero = Boolean.parseBoolean(RecordJsonConfig.getValue(RecordJsonConst.MOVE_FIRST_VIDEO_FRAME_TO_ZERO));
            this.defaultRecorder = Boolean.parseBoolean(RecordJsonConfig.getValue(RecordJsonConst.DEFAULT_RECORDER));
            this.segmentSize = Long.parseLong(RecordJsonConfig.getValue(RecordJsonConst.SEGMENT_SIZE));
            this.splitOnTcDiscontinuity = Boolean.parseBoolean(RecordJsonConfig.getValue(RecordJsonConst.SPLIT_ONE_TC_DISCONTTINUITY));
            this.backBufferTime = Integer.parseInt(RecordJsonConfig.getValue(RecordJsonConst.BACK_BUFFER_TIME));
            this.segmentationType = RecordJsonConfig.getValue(RecordJsonConst.SEGMENTTATION_TYPE);
            this.currentDuration = Long.parseLong(RecordJsonConfig.getValue(RecordJsonConst.CURRENT_DURATION));
            this.fileFormat = RecordJsonConfig.getValue(RecordJsonConst.FILE_FORMAT);
            this.option = RecordJsonConfig.getValue(RecordJsonConst.OPTION);
            this.segmentDuration = Long.parseLong(RecordJsonConfig.getValue(RecordJsonConst.SEGMENT_DURATION));
            this.recorderState = "";
            this.instanceName = "";
            this.serverName = "";
            this.fileVersionDelegateName = "";
            this.segmentSchedule = "";
            this.currentFile = "";
            this.saveFieldList = new String[]{""};
            this.applicationName = "";
            this.recorderErrorString = "";
            this.version = "";
            this.recordingStartTime = "";
            this.fileTemplate = "";
        }

        public Builder baseFile(String baseFile) {
            this.baseFile = baseFile;
            return this;
        }

        public Builder currentSize(Long currentSize) {
            this.currentSize = currentSize;
            return this;
        }

        public Builder startOnKeyFrame(boolean startOnKeyFrame) {
            this.startOnKeyFrame = startOnKeyFrame;
            return this;
        }

        public Builder outputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public Builder recordData(boolean recordData) {
            this.recordData = recordData;
            return this;
        }

        public Builder moveFirstVideoFrameToZero(boolean moveFirstVideoFrameToZero) {
            this.moveFirstVideoFrameToZero = moveFirstVideoFrameToZero;
            return this;
        }

        public Builder defaultRecorder(boolean defaultRecorder) {
            this.defaultRecorder = defaultRecorder;
            return this;
        }

        public Builder segmentSize(Long segmentSize) {
            this.segmentSize = segmentSize;
            return this;
        }

        public Builder splitOnTcDiscontinuity(boolean splitOnTcDiscontinuity) {
            this.splitOnTcDiscontinuity = splitOnTcDiscontinuity;
            return this;
        }

        public Builder backBufferTime(Integer backBufferTime) {
            this.backBufferTime = backBufferTime;
            return this;
        }

        public Builder segmentationType(String segmentationType) {
            this.segmentationType = segmentationType;
            return this;
        }

        public Builder currentDuration(Long currentDuration) {
            this.currentDuration = currentDuration;
            return this;
        }

        public Builder fileFormat(String fileFormat) {
            this.fileFormat = fileFormat;
            return this;
        }

        public Builder option(String option) {
            this.option = option;
            return this;
        }

        public Builder segmentDuration(Long segmentDuration) {
            this.segmentDuration = segmentDuration;
            return this;
        }

        public Builder recorderState(String recorderState) {
            this.recorderState = recorderState;
            return this;
        }

        public Builder instanceName(String instanceName) {
            this.instanceName = instanceName;
            return this;
        }

        public Builder serverName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public Builder fileVersionDelegateName(String fileVersionDelegateName) {
            this.fileVersionDelegateName = fileVersionDelegateName;
            return this;
        }

        public Builder segmentSchedule(String segmentSchedule) {
            this.segmentSchedule = segmentSchedule;
            return this;
        }

        public Builder currentFile(String currentFile) {
            this.currentFile = currentFile;
            return this;
        }

        public Builder saveFieldList(String[] saveFieldList) {
            this.saveFieldList = saveFieldList;
            return this;
        }

        public Builder applicationName(String applicationName) {
            this.applicationName = applicationName;
            return this;
        }

        public Builder recorderErrorString(String recorderErrorString) {
            this.recorderErrorString = recorderErrorString;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder recordingStartTime(String recordingStartTime) {
            this.recordingStartTime = recordingStartTime;
            return this;
        }

        public Builder fileTemplate(String fileTemplate) {
            this.fileTemplate = fileTemplate;
            return this;
        }

        public RecordJson build() {
            return new RecordJson(this);
        }
    }

    public String getInstanceName() {
        return instanceName;
    }

    public String getFileVersionDelegateName() {
        return fileVersionDelegateName;
    }

    public String getServerName() {
        return serverName;
    }

    public Long getCurrentSize() {
        return currentSize;
    }

    public String getSegmentSchedule() {
        return segmentSchedule;
    }

    public boolean isStartOnKeyFrame() {
        return startOnKeyFrame;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public String[] getSaveFieldList() {
        String[] clone = saveFieldList.clone();
        return clone;
    }

    public boolean isRecordData() {
        return recordData;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public boolean isMoveFirstVideoFrameToZero() {
        return moveFirstVideoFrameToZero;
    }

    public String getRecorderErrorString() {
        return recorderErrorString;
    }

    public Long getSegmentSize() {
        return segmentSize;
    }

    public boolean isDefaultRecorder() {
        return defaultRecorder;
    }

    public boolean isSplitOnTcDiscontinuity() {
        return splitOnTcDiscontinuity;
    }

    public String getVersion() {
        return version;
    }

    public String getBaseFile() {
        return baseFile;
    }

    public Long getSegmentDuration() {
        return segmentDuration;
    }

    public String getRecordingStartTime() {
        return recordingStartTime;
    }

    public String getFileTemplate() {
        return fileTemplate;
    }

    public Integer getBackBufferTime() {
        return backBufferTime;
    }

    public String getSegmentationType() {
        return segmentationType;
    }

    public Long getCurrentDuration() {
        return currentDuration;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public String getRecorderState() {
        return recorderState;
    }

    public String getOption() {
        return option;
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }

    public Map<String, Object> toJsonMap() {
        return JSONObject.parseObject(toJson(), Map.class);
    }

}
