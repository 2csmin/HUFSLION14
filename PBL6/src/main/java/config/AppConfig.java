package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.example.demo.MemberRepository;
import com.example.demo.MemberService;
import com.example.demo.MemoryMemberRepository;

// @Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
}