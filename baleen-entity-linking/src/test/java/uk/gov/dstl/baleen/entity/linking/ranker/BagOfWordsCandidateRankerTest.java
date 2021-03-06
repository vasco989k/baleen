// Copyright (c) Committed Software 2018, opensource@committed.io
package uk.gov.dstl.baleen.entity.linking.ranker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.jcas.JCas;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import uk.gov.dstl.baleen.annotators.testing.Annotations;
import uk.gov.dstl.baleen.entity.linking.Candidate;
import uk.gov.dstl.baleen.entity.linking.EntityInformation;
import uk.gov.dstl.baleen.entity.linking.util.DefaultCandidate;
import uk.gov.dstl.baleen.types.common.Person;
import uk.gov.dstl.baleen.types.language.Sentence;
import uk.gov.dstl.baleen.types.semantic.ReferenceTarget;

public class BagOfWordsCandidateRankerTest {

  private BagOfWordsCandidateRanker<Person> ranker;
  private EntityInformation<Person> entityInformation;

  @Before
  public void setup() throws UIMAException {
    ranker = new BagOfWordsCandidateRanker<Person>();
    ranker.initialize(ImmutableSet.of());

    JCas jCas = JCasFactory.createJCas();

    jCas.setDocumentText(
        "Sir John Major was Prime Minister of the United Kingdom. Major became Prime Minister after Thatcher resigned.");

    List<Sentence> s = Annotations.createSentences(jCas);

    Person j1 = Annotations.createPerson(jCas, 0, 14, "Sir John Major");
    Person j2 = Annotations.createPerson(jCas, 59, 64, "Major");
    ReferenceTarget jRT = Annotations.createReferenceTarget(jCas, j1, j2);

    entityInformation =
        new EntityInformation<>(jRT, ImmutableSet.of(j1, j2), ImmutableSet.copyOf(s));
  }

  @Test
  public void testRankerReturnsEmptyWhenNoCandiates() {
    assertFalse(ranker.getTopCandidate(entityInformation, ImmutableSet.of()).isPresent());
  }

  @Test
  public void testRankerReturnsCandidateWhenOnlyCandidate() {

    Candidate candidate = new DefaultCandidate("test", "name", ImmutableMap.of());

    Optional<Candidate> topCandidate =
        ranker.getTopCandidate(entityInformation, ImmutableSet.of(candidate));
    assertTrue(topCandidate.isPresent());
    assertEquals(candidate, topCandidate.get());
  }

  @Test
  public void testRankerReturnsBestMatchingCandidateObvious() {

    Candidate worst = new DefaultCandidate("worst", "worst", ImmutableMap.of());
    Candidate best =
        new DefaultCandidate(
            "best",
            "best",
            ImmutableMap.of(
                "firstname",
                "John",
                "surname",
                "Major",
                "abstract",
                "Prime Minister after Thatcher"));

    Optional<Candidate> topCandidate =
        ranker.getTopCandidate(entityInformation, ImmutableSet.of(worst, best));
    assertTrue(topCandidate.isPresent());
    assertEquals(best, topCandidate.get());
  }

  @Test
  public void testRankerReturnsBestMatchingCandidateDueToHigherRankingWords() {

    Candidate worst =
        new DefaultCandidate(
            "not bad",
            "not bad",
            ImmutableMap.of(
                "firstname",
                "James",
                "surname",
                "Major",
                "abstract",
                "Prime Minister of no where"));
    Candidate best =
        new DefaultCandidate(
            "best",
            "best",
            ImmutableMap.of(
                "firstname",
                "John",
                "surname",
                "Major",
                "abstract",
                "Prime Minister after Thatcher"));

    Optional<Candidate> topCandidate =
        ranker.getTopCandidate(entityInformation, ImmutableSet.of(worst, best));
    assertTrue(topCandidate.isPresent());
    assertEquals(best, topCandidate.get());
  }

  @Test
  public void testRankerReturnsExactMatchingCandidate() {

    Candidate worst = new DefaultCandidate("worst", "worst", ImmutableMap.of());
    Candidate best =
        new DefaultCandidate(
            "best",
            "best",
            ImmutableMap.of(
                "firstname",
                "John",
                "surname",
                "Major",
                "abstract",
                "Prime Minister after Thatcher"));
    Candidate exact =
        new DefaultCandidate(
            "exact",
            "Sir John Major",
            ImmutableMap.of(
                "firstname",
                "John",
                "surname",
                "Major",
                "abstract",
                "Prime Minister after Thatcher"));

    Optional<Candidate> topCandidate =
        ranker.getTopCandidate(entityInformation, ImmutableSet.of(worst, best, exact));
    assertTrue(topCandidate.isPresent());
    assertEquals(exact, topCandidate.get());
  }
}
