import csv
import nltk
import string
from gensim.test.utils import common_texts
from gensim.models import Word2Vec


with open('email_texts.csv', newline='') as csv_file:
    emails = csv.reader(csv_file, delimiter=',', quotechar='|')
    email_line = """"""
    tokens = []
    for row in emails:
        email_line = "".join(row)
        line_lowercase = email_line.lower()
        no_punctuation = "".join([char for char in line_lowercase if char not in string.punctuation])
        # print(no_punctuation)
        tokens += nltk.word_tokenize(no_punctuation)

    # print(tokens)
    tagged = nltk.pos_tag(tokens)
    # print(tagged)
    entities = nltk.chunk.ne_chunk(tagged)
    # print(entities)
    model = Word2Vec(entities, min_count=1, vector_size=50, workers=3, window=3, sg=1)
    print(model.wv.most_similar('mobile', topn=10))
