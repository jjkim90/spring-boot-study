package org.zerock.ex2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;
import org.zerock.ex2.repository.MemoRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInserDummies(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..."+i).writer("Writer..."+i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect(){

        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("--------------------------------------");
        if(result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Transactional
    @Test
    public void testSelect2(){

        Long mno = 100L;

        Memo memo = memoRepository.getReferenceById(mno);
        System.out.println("-------------------------------");
        System.out.println(memo);
    }

    @Test
    public void testUpdate(){
        Memo memo = Memo.builder().mno(100L).memoText("Update Text").writer("Update Writer").build();

        System.out.println(memoRepository.save(memo));
    }

    @Test
    public void testDelete(){

        Long mno = 100L;
        memoRepository.deleteById(mno);
    }
}
