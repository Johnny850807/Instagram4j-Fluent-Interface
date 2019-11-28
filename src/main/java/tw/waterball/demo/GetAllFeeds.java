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

import tw.waterball.api.Instagram;
import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramProperties;
import tw.waterball.api.InstagramSession;
import tw.waterball.impls.instagram4j.Instagram4JInstantiator;

import java.util.List;

public class GetAllFeeds {
    public static void main(String[] args) {
        Instagram ig = Instagram4JInstantiator.instantiate();

        // replace with your own username
        InstagramSession session = ig.login("dailymatchman", InstagramProperties.password("dailymatchman"));

        List<InstagramFeed> feeds = session.searchUser("dailymatchman")
                .getFeeds(Integer.MAX_VALUE);

        for (InstagramFeed feed : feeds) {
            System.out.println("==================================\n");
            System.out.println(feed.getContent());
        }

        System.out.println();
    }
}
