#!/usr/bin/env python

import requests

ENDPOINT = 'http://localhost:8080/lcs'

OK = 200
BAD_REQUEST = 400

CLIENT = requests.Session()

def no_body_fails():
    print('Make request with empty body')
    resp = CLIENT.post(ENDPOINT, data='', headers={'Content-Type': 'application/json'})
    if resp.status_code == BAD_REQUEST:
        print(f'Got bad request as expected: {resp.text}')
    else:
        print(f'UNEXPECTED: got {resp.status_code}')

def ill_formed_json_fails():
    print('Make request with ill-formed JSON')
    resp = CLIENT.post(ENDPOINT, data='{"foo"}', headers={'Content-Type': 'application/json'})
    if resp.status_code == BAD_REQUEST:
        print(f'Got bad request as expected: {resp.text}')
    else:
        print(f'UNEXPECTED: got {resp.status_code}')

def unexpected_json_fails():
    print('Make request with unexpected JSON')
    resp = CLIENT.post(ENDPOINT, data='{}', headers={'Content-Type': 'application/json'})
    if resp.status_code == BAD_REQUEST:
        print(f'Got bad request as expected: {resp.text}')
    else:
        print(f'UNEXPECTED: got {resp.status_code}')

def duplicate_in_list_fails():
    print('Make request with duplicate in list')
    request_dict = {
        'setOfStrings': [
            {'value': 'comcast'},
            {'value': 'comcastic'},
            {'value': 'broadcaster'},
            {'value': 'comcast'}
        ]
    }
    resp = CLIENT.post(ENDPOINT, json=request_dict)
    if resp.status_code == BAD_REQUEST:
        print(f'Got bad request as expected: {resp.text}')
    else:
        print(f'UNEXPECTED: got {resp.status_code}')

def example_list_succeeds():
    print('Make request with example list')
    request_dict = {
        'setOfStrings': [
            {'value': 'comcast'},
            {'value': 'comcastic'},
            {'value': 'broadcaster'}
        ]
    }
    resp = CLIENT.post(ENDPOINT, json=request_dict)
    if resp.status_code == OK:
        print(f'Got OK as expected: {resp.text}')
    else:
        print(f'UNEXPECTED: got {resp.status_code}')

if __name__ == '__main__':
    no_body_fails()
    ill_formed_json_fails()
    unexpected_json_fails()
    duplicate_in_list_fails()
    example_list_succeeds()
