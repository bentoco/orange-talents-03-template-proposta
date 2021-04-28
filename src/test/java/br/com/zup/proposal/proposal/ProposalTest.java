//package br.com.zup.proposal.proposal;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ProposalTest {
//
//    @Test
//    @DisplayName ( "should not be an eligible status" )
//    void test1 () {
//        Proposal
//                proposal =
//                new Proposal("70483426091" , "Foo Bar" , "foo@mail.com" , "Street of Life, 210" , BigDecimal.valueOf(2000d));
//        String withRestriction = "COM_RESTRICAO";
//        proposal.setStatus(withRestriction);
//
//        assertEquals(ProposalState.NAO_ELEGIVEL , proposal.getStatus());
//    }
//
//    @Test
//    @DisplayName ( "should be an eligible status" )
//    void test2 () {
//        Proposal
//                proposal =
//                new Proposal("70483426091" , "Foo Bar" , "foo@mail.com" , "Street of Life, 210" , BigDecimal.valueOf(2000d));
//        String withoutRestriction = "SEM_RESTRICAO";
//        proposal.setStatus(withoutRestriction);
//
//        assertEquals(ProposalState.ELEGIVEL , proposal.getStatus());
//    }
//}