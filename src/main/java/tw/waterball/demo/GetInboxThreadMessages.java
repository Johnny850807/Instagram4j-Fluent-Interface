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

import tw.waterball.api.*;
import tw.waterball.api.pagination.Page;
import tw.waterball.impls.instagram4j.Instagram4JInstantiator;

public class GetInboxThreadMessages {

    public static void main(String[] args) {
        Instagram ig = Instagram4JInstantiator.instantiate();

        // replace with your own username
        InstagramSession session = ig.login("watertroop3", InstagramProperties.password("watertroop3"));

        for (InstagramInboxThread thread : session.inbox().getRecentThreads()) {
            System.out.print("Thread [");
            thread.getUsers().forEach(user -> System.out.print(user.getUsername() + " "));
            System.out.println("]");

            for (Page<InstagramInboxThreadMessage> page : thread.getPagedAllMessages()) {
                System.out.println("=== New Page ===");
                for (InstagramInboxThreadMessage message : page) {
                    System.out.println(message.getContent());
                }
            }
        }
    }


}
