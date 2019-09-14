package com.bokecc.cloud.wowza.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Ffmpeg工具类
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class FfmpegUtils {
    /**
     * 视频转码
     * @param ffmpegPath    转码工具的存放路径
     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath    格式转换后的的文件保存路径
     * @param mediaPicPath    截图保存路径
     * @return
     * @throws Exception
     */
    public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
        String mediaPicPath) throws Exception {
        // 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<>();
        // 添加转换工具路径
        convert.add(ffmpegPath);
        // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add("-i");
        // 添加要转换格式的视频文件的路径
        convert.add(upFilePath);
        //指定转换的质量
        convert.add("-qscale");
        convert.add("6");
        //设置音频码率
        convert.add("-ab");
        convert.add("64");
        //设置声道数
        convert.add("-ac");
        convert.add("2");
        //设置声音的采样频率
        convert.add("-ar");
        convert.add("22050");
        //设置帧频
        convert.add("-r");
        convert.add("24");
        // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add("-y");
        convert.add(codcFilePath);

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add(upFilePath);
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("-ss");
        // 添加起始时间为第17秒
        cutpic.add("17");
        // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("-t");
        // 添加持续时间为1毫秒
        cutpic.add("0.001");
        // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("-s");
        // 添加截取的图片大小为350*240
        cutpic.add("800*280");
        // 添加截取的图片的保存路径
        cutpic.add(mediaPicPath);

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();

            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        return mark;
    }
}
