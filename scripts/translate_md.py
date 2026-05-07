import os
import re
import time
import sys
from deep_translator import GoogleTranslator

def translate_block(text, target_lang='es'):
    if not text.strip():
        return text
    try:
        translator = GoogleTranslator(source='auto', target=target_lang)
        return translator.translate(text)
    except Exception as e:
        print(f"Error translating block: {e}", file=sys.stderr)
        time.sleep(1)
        return text

def translate_file(filepath):
    print(f"Processing {filepath}...", flush=True)
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    segments = re.split(r'(```[\s\S]*?```)', content)
    translated_segments = []
    
    for segment in segments:
        if segment.startswith('```'):
            translated_segments.append(segment)
        else:
            if segment.startswith('---'):
                parts = re.split(r'(---[\s\S]*?---)', segment, maxsplit=1)
                if len(parts) > 1:
                    fm = parts[1]
                    rest = parts[2]
                    
                    fm_lines = fm.split('\n')
                    new_fm_lines = []
                    for line in fm_lines:
                        match = re.match(r'^(\s*(?:title|subtitle|previous_title|next_title):\s*)"?(.+?)"?$', line)
                        if match:
                            new_fm_lines.append(f'{match.group(1)}"{translate_block(match.group(2))}"')
                        else:
                            obj_match = re.match(r'^(\s*-\s*)"?(.+?)"?$', line)
                            if obj_match:
                                new_fm_lines.append(f'{obj_match.group(1)}"{translate_block(obj_match.group(2))}"')
                            else:
                                new_fm_lines.append(line)
                    
                    translated_segments.append('\n'.join(new_fm_lines))
                    segment = rest
                
            if segment.strip():
                chunk_size = 4000
                chunks = [segment[i:i+chunk_size] for i in range(0, len(segment), chunk_size)]
                translated_chunks = []
                for i, chunk in enumerate(chunks):
                    print(f"  Translating chunk {i+1}/{len(chunks)}...", flush=True)
                    translated_chunks.append(translate_block(chunk))
                    time.sleep(1) # Increased sleep to be safe
                translated_segments.append(''.join(translated_chunks))
            else:
                translated_segments.append(segment)

    output_path = filepath.replace('.md', '_es.md')
    with open(output_path, 'w', encoding='utf-8') as f:
        f.write(''.join(translated_segments))
    print(f"Saved to {output_path}", flush=True)

def main():
    target_dir = 'Teoria'
    files = [f for f in os.listdir(target_dir) if f.endswith('.md') and not f.endswith('_es.md')]
    files.sort()
    
    for filename in files:
        filepath = os.path.join(target_dir, filename)
        translate_file(filepath)

if __name__ == "__main__":
    main()
