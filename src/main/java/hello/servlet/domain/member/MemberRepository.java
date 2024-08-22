package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

        // 그리고 사실 싱글톤이기 때문에 필드들이 static일 필요가 없다. 그러나 그냥 써줌 !!
        private static Map<Long, Member> store = new HashMap<>();
        private static long sequence = 0L;

        // 싱글톤으로 만들어준다.
        private static final MemberRepository instance = new MemberRepository();

        public static MemberRepository getInstance() {
            return instance;
        }

        // 싱글톤이려면 기본 생성자를 private으로 막아주기
        private MemberRepository() {
        }

        public Member save(Member member) {
            member.setId(++sequence);
            store.put(member.getId(), member);
            return member;
        }

        public Member findById(Long id) {
            return store.get(id);
        }

        public List<Member> findAll() {
            return new ArrayList<>(store.values());
        }

        // test 할 때 많이 사용됨
        public void clearStore() {
            store.clear();
        }
}
