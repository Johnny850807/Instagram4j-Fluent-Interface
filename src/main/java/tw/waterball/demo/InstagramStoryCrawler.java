/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tw.waterball.demo;

/**
 * Not yet implemented, Instagram4J doesn't support story's url handling.
 * The code below uses the contributed version : https://github.com/jvogit/instagram4j
 * Which made story crawling work.
 * @author johnny850807 (Waterball)
 */
public class InstagramStoryCrawler {
//    private final String USERNAME;
//    private final String DIRNAME_STORIES_REPOSITORY;
//    private Instagram4j instagram;
//    private InstagramUser user;
//    private int currentBatchNewStories = 0;
//
//    public InstagramStoryCrawler(String username) {
//        this.USERNAME = username;
//        this.DIRNAME_STORIES_REPOSITORY = USERNAME + "/stories";
//    }
//
//    public void start() throws IOException, InterruptedException {
//        System.out.println("Crawling " + USERNAME + " ...");
//        instagram = login();
//        user = getUser(USERNAME);
//        createUserDirectoryIfNotExists();
//
//        while(true) {
//            currentBatchNewStories = 0;
//            getStoriesFromUser().stream()
//                    .map(this::getUrlFromStory)
//                    .filter(Objects::nonNull)      // filter out invalid URL's
//                    .forEach(this::downloadMediaFromURL);
//
//            System.out.println("Got " + currentBatchNewStories + " new stories in this batch.");
//            System.out.println("Sleeping five minutes ... ");
//            TimeUnit.MINUTES.sleep(5);
//        }
//    }
//
//    private void createUserDirectoryIfNotExists() throws IOException {
//        if (Files.notExists(Paths.get(USERNAME)))
//        {
//            Files.createDirectory(Paths.get(USERNAME));
//            Files.createDirectory(Paths.get(DIRNAME_STORIES_REPOSITORY));
//        }
//    }
//
//
//    private Instagram4j login() throws IOException {
//        String username = "dailymatchman";
//        String password = InstagramProperties.password("dailymatchman");
//
//        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
//        instagram.setup();
//        instagram.login();
//        return instagram;
//    }
//
//    private InstagramUser getUser(String username) throws IOException {
//        return instagram.sendRequest(new InstagramSearchUsernameRequest(username)).getUser();
//    }
//
//    private List<InstagramStoryItem> getStoriesFromUser() throws IOException {
//        InstagramUserReelMediaFeedResult result = instagram.sendRequest(
//                new InstagramGetUserReelMediaFeedRequest(user.getPK()));
//        return result.getItems();
//    }
//
//
//    private URL getUrlFromStory(InstagramStoryItem item) {
//        try {
//            switch (item.media_type) {
//                case InstagramItem.PHOTO:
//                    return new URL(item.image_versions2.candidates.get(0).getUrl());
//                case InstagramItem.VIDEO:
//                    return new URL(item.video_versions.get(0).getUrl());
//            }
//        } catch (MalformedURLException err) {
//            return null;
//        }
//        return null;
//    }
//
//    private void downloadMediaFromURL(URL url) {
//        String fileName = FilenameUtils.getName(url.getPath());
//        File targetFile = Paths.get(DIRNAME_STORIES_REPOSITORY, fileName).toFile();
//        if (!targetFile.exists())
//        {
//            try {
//                System.out.println("Download media into " + targetFile + " ...");
//                FileUtils.copyURLToFile(url, targetFile, 60000, 60000);
//                currentBatchNewStories ++;
//            } catch (IOException err) {
//                err.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException, IOException {
//        new InstagramStoryCrawler("c.seulcoin").start();
//    }

}
