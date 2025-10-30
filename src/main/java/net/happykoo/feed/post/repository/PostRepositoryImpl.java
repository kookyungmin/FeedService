package net.happykoo.feed.post.repository;

import lombok.RequiredArgsConstructor;
import net.happykoo.feed.post.application.interfaces.PostRepository;
import net.happykoo.feed.post.domain.Post;
import net.happykoo.feed.post.repository.entity.post.PostEntity;
import net.happykoo.feed.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
//        영속성 Context 가 새로 열렸고, PostEntity 가 없기에 merge 하면서 다시 select query를 날리고 update 하게 됨
//        jpaPostRepository.save(postEntity);
        if (postEntity.getId() != null) {
            //JPQL 로 update 하게 변경
            jpaPostRepository.updatePostEntity(postEntity);
        } else {
            jpaPostRepository.save(postEntity);
        }

        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        return jpaPostRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new)
                .toPost();
    }
}
