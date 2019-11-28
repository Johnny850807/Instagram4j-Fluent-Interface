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

package tw.waterball.api.foreach;

import tw.waterball.api.InstagramInboxThread;
import tw.waterball.api.InstagramPendingInboxThread;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author johnny850807 (Waterball)
 */
public class ForeachPendingThread extends ForeachThread {
    private List<? extends InstagramPendingInboxThread> items;

    public ForeachPendingThread(List<? extends InstagramPendingInboxThread> items) {
        super(items);
        this.items = items;
    }
    public ForeachPendingThread filter(Predicate<? super InstagramInboxThread> predicate) {
        return new ForeachPendingThread(
                items.stream().filter(predicate).collect(Collectors.toList())
        );
    }

    public ForeachThread approve() {
        return new ForeachThread(items.stream()
                        .map(InstagramPendingInboxThread::approve)
                        .collect(Collectors.toList()));
    }

    public ForeachThread decline() {
        return new ForeachThread(items.stream()
                        .map(InstagramPendingInboxThread::decline)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<? extends InstagramPendingInboxThread> get() {
        return items;
    }
}
