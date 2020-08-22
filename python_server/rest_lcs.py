#!/usr/bin/env python
"""
REST service for longest common substring (LCS).

There is a static page at / or /index.html to exercise the service.
"""

import json
import os.path
import sys

import bottle

STATIC_PATH = os.path.join(os.path.abspath(os.path.dirname(sys.argv[0])), 'static')

@bottle.post('/lcs')
def calculate_lcs():
    body = bottle.request.body.read().decode('utf-8')
    if not body:
        bottle.abort(400, 'Request body should not be empty')

    try:
        req = json.loads(body)
    except json.decoder.JSONDecodeError:
        bottle.abort(400, 'Request is not valid JSON')

    try:
        string_values = req['setOfStrings']
    except KeyError:
        bottle.abort(400, 'Request JSON not structured correctly (1)')

    if not isinstance(string_values, list):
        bottle.abort(400, 'Request JSON not structured correctly (2)')

    try:
        strings = [v['value'] for v in string_values]
    except KeyError:
        bottle.abort(400, 'Request JSON not structured correctly (3)')

    string_set = set(strings)
    if len(string_set) != len(strings):
        bottle.abort(400, 'At least one string in the request is a duplicate')

    result = lcs(strings)
    resp = {'lcs': [{'value': result}]}
    bottle.response.content_type = 'application/json'
    return json.dumps(resp)

@bottle.get('/')
@bottle.get('/index.html')
def index():
    return bottle.static_file('index.html', root=STATIC_PATH)

@bottle.get('/static/<filepath:path>')
def server_static(filepath):
    return bottle.static_file(filepath, root=STATIC_PATH)

def lcs(strings):
    """
    Calculate the longest common substring (LCS).

    Checks every substring of the first string for existence in all the other strings.  If
    multiple substrings have the same length as the longest, the first one encountered is
    returned.

    Possible optimization: use the shortest string as the "reference string".

    Originally from https://www.geeksforgeeks.org/longest-common-substring-array-strings/
    but fixed to detect when the stem is not found in the last input string.

    :param strings: List of strings.
    :return: The longest common substring.
    """
    n = len(strings) 
    if n == 0:
        return ""

    # our reference string
    s = strings[0] 
    l = len(s)
  
    res = "" 
  
    for i in range(l) : 
        for j in range(i + 1, l + 1) : 
  
            # generating all possible substrings 
            # of our reference string strings[0] i.e s 
            stem = s[i:j]
            k = 1
            for k in range(1, n):  
  
                # Check if the generated stem is 
                # common to all words 
                if stem not in strings[k]: 
                    break
              
            # If current substring is present in 
            # all strings and its length is greater  
            # than current result 
            else:
                if len(res) < len(stem):
                    res = stem 
  
    return res 

if __name__ == '__main__':
    bottle.run(host='localhost', port=8080)
